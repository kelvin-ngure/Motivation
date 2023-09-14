package com.happymeerkat.motivated.ui.views

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.happymeerkat.motivated.ui.theme.MotivatedDailyQuotesTheme
import com.happymeerkat.motivated.ui.views.navigation.RootNavigation
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


sealed class ImageState {
    class ImageDownloaded(val downloadedImageFile: File): ImageState()
    object ImageUploaded: ImageState()
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)

    var imageState = mutableStateOf<ImageState>(ImageState.ImageUploaded)


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )


        super.onCreate(savedInstanceState)
        //configureAmplify()

        setContent {
            MotivatedDailyQuotesTheme {

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    RootNavigation()
//                    imageState = imageState,
//                    downloadImage = { awsKey -> downloadPhoto(awsKey)}

                }
            }
        }
    }


//    private fun configureAmplify() {
//        try {
//
//            Amplify.addPlugin(AWSCognitoAuthPlugin())
//            Amplify.addPlugin(AWSS3StoragePlugin())
//            Amplify.configure(applicationContext)
//
//            Amplify.Auth.fetchAuthSession(
//                { Log.i("AMPLIFY", "Auth session = $it") },
//                { error -> Log.e("AMPLIFY", "Failed to fetch auth session", error) }
//            )
//
//
//        } catch (e: AmplifyException) {
//            Log.d("AMPLIFY", "couldn't initialize $e")
//        }
//    }

//    private fun uploadFile() {
//        val exampleFile = File(applicationContext.filesDir, "ExampleKey")
//        exampleFile.writeText("Example file contents")
//
//        Amplify.Storage.uploadFile("ExampleKey", exampleFile,
//            { Log.i("MyAmplifyApp", "Successfully uploaded: ${it.key}") },
//            { Log.e("MyAmplifyApp", "Upload failed", it) }
//        )
//    }

//    @RequiresApi(Build.VERSION_CODES.R)
//    fun downloadPhoto(awsKey: String) {
//        val localFile = File("${applicationContext.filesDir}/$awsKey")
//        Log.d("AMPLIFY", "downloading")
//        Amplify.Storage.downloadFile(
//            "$awsKey.jpg",
//            localFile,
//            { imageState.value = ImageState.ImageDownloaded(localFile); Log.d("AMPLIFY", "download success") },
//            { Log.e("AMPLIFY", "Failed download", it) }
//        )
//    }
//
//    @RequiresApi(Build.VERSION_CODES.R)
//    fun listKeys() {
//        val options = StoragePagedListOptions.builder()
//            .setPageSize(1000)
//            .build()
//
//        Amplify.Storage.list("", options,
//            { result ->
//                result.items.forEach { item ->
//                    Log.i("MyAmplifyApp", "Item: ${item.key}")
//                }
//                Log.i("MyAmplifyApp", "Next Token: ${result.nextToken}")
//            },
//            { Log.e("MyAmplifyApp", "List failure", it) }
//        )
//    }
}