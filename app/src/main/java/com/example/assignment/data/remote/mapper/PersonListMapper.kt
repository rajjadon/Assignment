package com.example.assignment.data.remote.mapper

import android.content.Context
import com.example.assignment.R
import com.example.assignment.common.AppConstant.APP_DATE_FORMAT
import com.example.assignment.common.AppConstant.APP_SERVER_DATE_FORMAT
import com.example.assignment.common.utills.getAppDateFromServerDate
import com.example.assignment.data.model.BaseResponse
import com.example.assignment.data.model.Person
import com.example.assignment.data.remote.apiHelper.EntityMapper
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import javax.inject.Inject
import javax.inject.Named

class PersonListMapper @Inject constructor(
    @ApplicationContext private val context: Context,
    @Named(value = APP_SERVER_DATE_FORMAT)
    private val serverDateFormat: SimpleDateFormat,
    @Named(value = APP_DATE_FORMAT)
    private val appDateFormat: SimpleDateFormat
) : EntityMapper<List<Person>, BaseResponse<List<Person>>> {
    override suspend fun mapFromEntity(entity: List<Person>): BaseResponse<List<Person>> {
        return BaseResponse(

            success = true,
            message = "success",
            data = entity.map { person ->

                person.apply {
                    birthdayInfo.dobDate = getAppDateFromServerDate(
                        birthdayInfo.dobDate,
                        appDateFormat,
                        serverDateFormat
                    )

                    personName.apply {
                        personName.nameTitle = "$nameTitle $firstName $lastName"
                    }

                    colorId = when {
                        requestTittle.equals(
                            context.getString(R.string.accept_text),
                            true
                        ) -> context.getColor(R.color.green)
                        requestTittle.equals(
                            context.getString(R.string.decline_text),
                            true
                        ) -> context.getColor(R.color.red)
                        else -> context.getColor(R.color.transparent)
                    }

                    person.personLocation.apply {
                        city = "$city $state $country"
                    }

                    userRegisteredDetails.registrationDate = getAppDateFromServerDate(
                        userRegisteredDetails.registrationDate,
                        appDateFormat,
                        serverDateFormat
                    )
                }
                person
            }
        )
    }

    override suspend fun mapToEntity(domainModel: BaseResponse<List<Person>>): List<Person> {
        TODO("Not yet implemented")
    }
}