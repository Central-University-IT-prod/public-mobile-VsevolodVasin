package prodcontest.lifestylehub.presentation.utils

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("ConstantLocale")
val dateFormat = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
@SuppressLint("ConstantLocale")
val uiDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())