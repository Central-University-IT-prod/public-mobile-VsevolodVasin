package prodcontest.domain.leisure.model

data class LeisureModel(
    val id : Int? = null,
    val name : String,
    val description : String,
    val date : String,
    val recommendation : String? = null
)
