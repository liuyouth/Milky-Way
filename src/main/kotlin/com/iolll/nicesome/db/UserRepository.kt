package com.iolll.nicesome.db

import com.iolll.nicesome.model.base.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserRepository : CrudRepository<User, Long> {
//    fun findByLastName(lastName: String): List<User>
//
//    @Query("from User u where u.firstName=:name")
//    fun findUser(@Param("name") name: String): List<User>
}