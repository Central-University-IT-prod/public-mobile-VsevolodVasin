package prodcontest.lifestylehub.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.koinViewModel
import prodcontest.lifestylehub.R

@Composable
fun AuthScreen(navController: NavController) {
    val vm = koinViewModel<AuthViewModel>()
    val user = vm.user 
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.Center) {
        if (user is UserUiState.Authed) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    GlideImage(
                        modifier = Modifier
                            .size(80.dp)
                            .padding(top=32.dp),
                        imageModel = { user.user.picture },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Fit,
                            alignment = Alignment.Center
                        )
                    )
                    Text(text = user.user.login, modifier = Modifier.wrapContentSize())
                }
                Text(text = user.user.email, modifier = Modifier.wrapContentSize())
                Text(text = user.user.phone, modifier = Modifier.wrapContentSize())
                Text(text = user.user.gender, modifier = Modifier.wrapContentSize())

            }
        } else {
            var loginForm by remember { mutableStateOf(false) }
            if (loginForm) {
                LoginUserForm {
                    loginForm = false
                }
            } else {
                RegisterUserForm {
                    loginForm = true
                }
            }
        }
    }
}

@Composable
fun RegisterUserForm(loginClick : () -> Unit ) {
    val vm = koinViewModel<AuthViewModel>()
    OutlinedCard(Modifier.wrapContentSize()) {
        Column(Modifier.padding(vertical = 24.dp, horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            var password by remember { mutableStateOf("") }

            OutlinedTextField(value = password, onValueChange = { password = it }, label = {
                Text(stringResource(id = R.string.password), style= MaterialTheme.typography.titleSmall)
            })

            Button(onClick = {
                vm.registerUser(password)
            }) {
                Text(
                    stringResource(id = R.string.register),
                    style= MaterialTheme.typography.titleMedium
                )
            }

            Button(onClick = {
                loginClick()
            }) {
                Text(
                    stringResource(id = R.string.have_account),
                    style= MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun LoginUserForm(registerClick : () -> Unit ) {
    val vm = koinViewModel<AuthViewModel>()
    OutlinedCard(Modifier.wrapContentSize()) {
        Column(Modifier.padding(vertical = 24.dp, horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            var login by remember { mutableStateOf("") }

            OutlinedTextField(value = login, onValueChange = { login = it }, label = {
                Text(stringResource(id = R.string.login), style= MaterialTheme.typography.titleSmall)
            })
            
            var password by remember { mutableStateOf("") }

            OutlinedTextField(value = password, onValueChange = { password = it }, label = {
                Text(stringResource(id = R.string.password), style= MaterialTheme.typography.titleSmall)
            })

            Button(onClick = {
                vm.loginUser(login, password)
            }) {
                Text(
                    stringResource(id = R.string.auth),
                    style= MaterialTheme.typography.titleMedium
                )
            }

            Button(onClick = {
               registerClick()
            }) {
                Text(
                    stringResource(id = R.string.not_account),
                    style= MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}