import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import ru.netology.pusher.token
import java.io.FileInputStream

fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
       // .setDatabaseUrl(dbUrl)
        .build()

    FirebaseApp.initializeApp(options)

    val messageLike = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Nedd",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()

    val messagePost = Message.builder()
        .putData("action", "POST")
        .putData("content", """{
            "userId": 2,
            "userName": "Максим",
            "postId": 3,
            "content": "Мой новый пост готов! Ура! Ура! Мы едем в Ленинград!"
            }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(messagePost)
}