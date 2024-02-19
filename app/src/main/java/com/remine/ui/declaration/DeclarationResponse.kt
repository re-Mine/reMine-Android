package com.remine.ui.declaration

data class DeclarationResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: DeclarationRes,
)

data class DeclarationRes(
    val memberName: String,
    val todayParticipantsCount: Int,
    val declarationList: List<Declaration>
)
