package com.leotesta017.clinicapenal.model.modelUsuario


data class Case (
    val case_id: String = "",
    var isRepresented: Boolean = false,
    val lawyerAssigned: String = "",
    val place: String = "",
    val situation: String = "",
    val state: String = "",
    val studentAssigned: String = "",
    val listAppointments: List<String> = emptyList(),
    val listExtraInfo: List<String> = emptyList(),
    val listComents: List<String> = emptyList()
)