package com.myuidemo.api.response;

import com.myuidemo.api.response.Status.*


class Resource<T>(status: Status  ,data : T? ,message: String?) {


     var status: Status = Status.IDEL

    var message: String? = ""

    var data : T? = null

    var refresh_jwt_token = ""

    init {
        this.status = status
        this.message = message
        this.data = data
    }

    fun Resource (status: Status, data: T?, message: String?) {
        this.status = status
        this.data = data
        this.message = message
    }

    constructor(status: Status, data: T?, message: String?, refresh_jwt_data : String?) : this(status,data,message) {
        this.status = status
        this.data = data
        this.message = message
        this.refresh_jwt_token = refresh_jwt_data!!
    }

    /*fun Resource(status: Status){
        this.status = status
        this.data = null
        this.message = ""
    }
    */
    fun <T> success(data: T?) : Resource<T?> {

        return  Resource(Status.SUCCESS,data,"")
    }

    fun <T> error(msg: String?, data: T?): Resource<T?> {
        return  Resource(ERROR,data,msg)
    }

    fun <T> loading(data: T?): Resource<T?> {
        return Resource(Status.LOADING, data,"")
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }

        val resource = o as Resource<*>?

        if (status !== resource!!.status) {
            return false
        }
        if (if (message != null) message != resource!!.message else resource!!.message != null) {
            return false
        }
        return if (data != null) data == resource!!.data else resource!!.data == null
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (message?.hashCode() ?: 0)
        result = 31 * result + if (data != null) data!!.hashCode() else 0
        return result
    }

    override fun toString(): String {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\''.toString() +
                ", data=" + data +
                '}'.toString()
    }
}
