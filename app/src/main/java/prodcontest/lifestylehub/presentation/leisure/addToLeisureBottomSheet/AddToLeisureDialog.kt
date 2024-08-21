package prodcontest.lifestylehub.presentation.leisure.addToLeisureBottomSheet

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import prodcontest.domain.leisure.model.LeisureModel
import prodcontest.domain.recommendation.model.RecommendationDetailsModel
import prodcontest.lifestylehub.R
import prodcontest.lifestylehub.presentation.utils.DateTransformation
import prodcontest.lifestylehub.presentation.utils.dateFormat
import prodcontest.lifestylehub.presentation.utils.uiDateFormat


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToLeisureDialog(recommendation : RecommendationDetailsModel? = null, add: (LeisureModel) -> Unit, dismiss: () -> Unit) {
    val context = LocalContext.current
    ModalBottomSheet(onDismissRequest = {
        dismiss()
    }) {
       Scaffold { paddingValues ->
           Column(
               Modifier
                   .padding(paddingValues)
                   .padding(horizontal = 16.dp),
               verticalArrangement = Arrangement.spacedBy(8.dp)

            ) {
               var name by remember { mutableStateOf(recommendation?.name ?: "") }
               var date by remember { mutableStateOf("") }
               var description by remember { mutableStateOf("") }

               OutlinedTextField(value = name, onValueChange = { name = it }, label = {
                   Text(stringResource(id = R.string.name), style= MaterialTheme.typography.titleSmall)
               })

               OutlinedTextField(
                   value = description,
                   onValueChange = { description = it },
                   label = {
                    Text(stringResource(id = R.string.description), style= MaterialTheme.typography.titleSmall)
                    },
                   minLines = 3,
                   maxLines = 5
               )

               OutlinedTextField(
                   value = date,
                   onValueChange = { if (it.length<=8) { date = it } },
                   visualTransformation = DateTransformation(),
                   label = {
                    Text(stringResource(id = R.string.date), style= MaterialTheme.typography.titleSmall)
                   }
               )

               Button(onClick = {
                    try {
                        val date = dateFormat.parse(date)!!

                        add(
                            LeisureModel(
                                date= uiDateFormat.format(date),
                                name=name,
                                description=description,
                                recommendation = recommendation?.id,
                            )
                        )

                        dismiss()
                    } catch (e : Exception) {
                        Toast.makeText(context, context.getString(R.string.date_not_correct), Toast.LENGTH_LONG).show()
                    }
               }) {
                   Text(
                       stringResource(id = R.string.add),
                       style= MaterialTheme.typography.titleMedium
                   )
               }
           }
       }
    }
}