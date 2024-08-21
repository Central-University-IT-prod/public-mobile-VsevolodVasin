package prodcontest.data.leisure.dbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "leisure")
data class LeisureDatabaseModel(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "description") val description : String,
    @ColumnInfo(name = "date") val date : String,
    @ColumnInfo(name = "recommendation") val recommendation : String? = null
)