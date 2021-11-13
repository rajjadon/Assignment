package com.example.assignment.ui

import com.example.assignment.common.MarkerInterface
import com.example.assignment.data.model.PersonLocal

interface MatchOperation : MarkerInterface {
    fun acceptRequest(person: PersonLocal)
    fun declineRequest(person: PersonLocal)
}