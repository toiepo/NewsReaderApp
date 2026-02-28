import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.newsreaderapp.ui_app.model.NewsUiModel

@Composable
fun NewsDetailScreen(news: NewsUiModel, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        BackHandler {
            onBack()
        }

        AsyncImage(
            model = news.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = news.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = news.sourceName, color = MaterialTheme.colorScheme.primary)
                Text(text = news.publishedAt.take(10))
            }

            Divider(modifier = Modifier.padding(vertical = 12.dp))


            Text(text = news.description, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(24.dp))


            val context = LocalContext.current
            Button(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, news.url.toUri())
                context.startActivity(intent)
            }) {
                Text("Read the document from resource")
            }
        }
    }
}