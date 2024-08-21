package prodcontest.data.recommendations.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "recommendations")
data class RecommendationDetailsDatabaseModel(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "json") val json: String,
    @ColumnInfo(name= "timeStamp") val timeStamp : Long
)