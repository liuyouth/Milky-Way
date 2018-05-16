package com.iolll.nicesome.controller

import com.iolll.nicesome.db.UrlRecordRepository
import com.iolll.nicesome.model.base.PageResult
import com.iolll.nicesome.model.base.RBuilder
import com.iolll.nicesome.model.base.RBuilder.Seccess
import com.iolll.nicesome.model.base.Result
import com.iolll.nicesome.model.base.UrlRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.util.StringUtils.isEmpty
import java.util.ArrayList
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import java.math.BigInteger


@RestController
@RequestMapping("/urlrecord/")
class UrlRecordController {
    @Autowired
    lateinit var repository: UrlRecordRepository
    @Autowired
    @PersistenceContext
    lateinit var entityManager: EntityManager

    @GetMapping("/")
    fun list(@RequestParam(value = "name", defaultValue = "") name: String,
             @RequestParam(value = "type", defaultValue = "") type: String,
             @RequestParam(value = "page", defaultValue = "0") page: Int,
             @RequestParam(value = "size", defaultValue = "15") size: Int,
             @RequestParam(value = "userid", defaultValue = "0") userId: Long,
             @RequestParam(value = "sortField", defaultValue = "") filedName: String,
             @RequestParam(value = "sortOrder", defaultValue = "") sortOrder: String): PageResult<UrlRecord> {
        val pageNum = if (page == 0) {
            0
        } else {
            page - 1
        }
        var filedNames: String = filedName
        if (isEmpty(filedName) || "null" == filedName)
            filedNames = "id"
        var sd = when (sortOrder) {
            "descend" -> Sort.Direction.DESC
            else -> Sort.Direction.ASC
        }

        var totalSql = getSql("select count(id) from `url_record`", userId, type, filedNames, name, sd, pageNum, size)
        var query = entityManager?.createNativeQuery(totalSql)
        val total = query.singleResult as BigInteger
        var allPage = (total.toInt() / 3)

        var sql = getSql("select * from `url_record`", userId, type, filedNames, name, sd, pageNum, size)
        var q = entityManager?.createNativeQuery(sql, UrlRecord::class.java)
        var list: List<UrlRecord> = q.resultList.toList() as List<UrlRecord>

        return RBuilder.Seccess(list, total.toLong(), allPage)
        //        return RBuilder.Seccess(list, data.totalElements, data.totalPages)
        //        val sort = Sort(sd, filedNames)
//        val pageable = PageRequest(pageNum, size, sort)
//        val data = if (userId==0L) {
//
//            if (isEmpty(name) && isEmpty(type)) {
//                repository.findAll(pageable)
//            } else if (isEmpty(type)) {
//                repository.findByNameLike(name, pageable)
//            } else {
//                repository.findByTypeLike(type, pageable)
//            }
//        }else{
//             if (isEmpty(name) && isEmpty(type)) {
//                repository.findByUser_id(userId,pageable)
//            } else if (isEmpty(type)) {
//                repository.findByNameLike(name, pageable)
//            } else {
//                repository.findByTypeLike(type, pageable)
//            }
//        }
    }

    fun getSql(sqlte: String, userId: Long, type: String, filedNames: String, name: String, sd: Sort.Direction, page: Int, size: Int): String {
        var sql = sqlte
        if (userId != 0L)
            sql += " where user_id =" + userId + " "
        if (!isEmpty(type))
            sql += " and type =" + type + " "
        if (!isEmpty(name))
            sql += " and name like %" + name + "% "
        sql += " order by " + filedNames + " " + sd
        sql += " limit " + page + "," + size
        println(sql)
        return sql
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long): UrlRecord {
        return repository.findOne(id)
    }

    @PostMapping("/")
    fun add(@RequestBody body: UrlRecord): UrlRecord {
        body.id = 0L
        return repository.save(body)
    }

    @PutMapping("/")
    fun update(@RequestBody body: UrlRecord): UrlRecord {
        return repository.save(body)
    }

    @DeleteMapping("/{id}")
    fun del(@PathVariable("id") id: Long) {
        return repository.delete(id)
    }


}

