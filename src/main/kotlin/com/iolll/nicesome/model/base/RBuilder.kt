package com.iolll.nicesome.model.base

import java.util.ArrayList

object RBuilder {
    fun <T> Seccess(data: T): Result<T> {
        return Result<T>().setCode(200).setMsg("请求成功！").setData(data)
    }

    fun <T> Seccess(data: List<T>, total: Long, allPage: Int): PageResult<T> {
        return PageResult<T>().setCode(200).setMsg("请求成功！").setData(data).setTotal(total).setAllPage(allPage)
    }
}
