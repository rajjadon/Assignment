package com.example.assignment.ui

import com.example.assignment.common.MarkerInterface
import com.example.assignment.data.model.Person

interface MatchOperation : MarkerInterface {
    fun acceptRequest(person: Person)
    fun declineRequest(person: Person)
}