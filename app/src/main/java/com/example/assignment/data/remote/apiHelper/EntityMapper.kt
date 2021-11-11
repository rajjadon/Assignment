/*
 * Created by Raj Pratap Singh Jadon on 13/08/21, 5:36 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 02/08/21, 12:27 PM
 */

package com.example.assignment.data.remote.apiHelper

import com.example.assignment.common.MarkerInterface

interface EntityMapper<Entity, DomainModel> : MarkerInterface {
    suspend fun mapFromEntity(entity: Entity): DomainModel
    suspend fun mapToEntity(domainModel: DomainModel): Entity
}