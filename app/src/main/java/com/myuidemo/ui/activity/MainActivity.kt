package com.myuidemo.ui.activity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.myuidemo.R
import com.myuidemo.api.response.Resource
import com.myuidemo.api.response.Status
import com.myuidemo.api.response.TeamResponse
import com.myuidemo.data.factory.TeamModelFactory
import com.myuidemo.databinding.ActivityMainBinding

import com.myuidemo.myviewmodel.TeamViewModel
import com.myuidemo.ui.adaptor.TeamRecyclerViewAdaptor
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*

import javax.inject.Inject


class MainActivity : BaseActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var userdetailFactory: TeamModelFactory
    lateinit var viewModel: TeamViewModel
    lateinit var resourceTeamLiveData: LiveData<Resource<TeamResponse>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.activity = this
        binding.onclickListener = this
        setToolbar()
        setTimer()
        configureDagger()
        initViewModel()
        oberveUserLogin()
        viewModel.getTeamDetail()

    }

    fun initViewModel() {
        viewModel =
            ViewModelProviders.of(this, userdetailFactory).get(TeamViewModel::class.java)
    }

    fun configureDagger() {
        AndroidInjection.inject(this)
    }

    fun setTimer(){
        object : CountDownTimer(30*60000
            , 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes: Long = millisUntilFinished / 1000 / 60
                val seconds = (millisUntilFinished / 1000 % 60)
                binding.timer.setText(minutes.toString()+" : "+seconds.toString())
                           }

            override fun onFinish() {

            }
        }.start()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onClick(v: View?) {
        viewModel.getTeamDetail()
    }


    fun setToolbar(){
        setSupportActionBar(binding.toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);
        getSupportActionBar()!!.setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar);
        binding.toolbar.setTitle("");
        binding.toolbar.setSubtitle("");
    }


    fun oberveUserLogin() {
        resourceTeamLiveData = viewModel.getTeamObserver()
        resourceTeamLiveData.observe(this, Observer
        { t ->
            when (t?.status) {

                Status.IDEL -> {

                }

                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.content.visibility = View.GONE

                }

                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    binding.content.visibility = View.VISIBLE
                    if (t.data != null) {

                        setRecyclerView(binding.wicketRecycler,t.data!!.wicketkeeper)
                        setRecyclerView(binding.batterRecycler,t.data!!.batsman)
                        setRecyclerView(binding.rounderRecycler,t.data!!.allrounder)
                        setRecyclerView(binding.bowlerRecycler,t.data!!.bowler)
                    } else {
                        showToast(t.message!!)
                    }
                }

                Status.ERROR -> {
                }

                Status.FORCE_UPDATE -> {

                }
                else -> {
                }

            }
        })

    }

    fun setRecyclerView(
        recyclerView:RecyclerView,
        list: ArrayList<com.myuidemo.api.response.PlayerModel>?
    ){

        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        recyclerView.adapter = TeamRecyclerViewAdaptor(list,this)

    }






}
