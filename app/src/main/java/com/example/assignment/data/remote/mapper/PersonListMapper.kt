package com.example.assignment.data.remote.mapper

import com.example.assignment.data.model.BaseResponse
import com.example.assignment.data.model.Person
import com.example.assignment.data.remote.apiHelper.EntityMapper

class PersonListMapper : EntityMapper<List<Person>, BaseResponse<List<Person>>> {
    override suspend fun mapFromEntity(entity: List<Person>): BaseResponse<List<Person>> {
        return BaseResponse(

            success = true,
            message = "success",
            data = entity.map { person ->


                person
            }
        )
    }

    override suspend fun mapToEntity(domainModel: BaseResponse<List<Person>>): List<Person> {
        TODO("Not yet implemented")
    }
}