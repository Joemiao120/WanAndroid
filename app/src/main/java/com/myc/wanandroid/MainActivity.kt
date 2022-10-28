package com.myc.wanandroid

import SampleData
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.myc.wanandroid.ui.theme.WanAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanAndroidTheme {
                Conversation(message = SampleData.conversationSample)
            }

        }
    }
}

@Composable
fun MessageCard(msg: Message) {

    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        SpacerWithWidth(width = 8.dp)

        var isExpanded by remember {
            mutableStateOf(false)
        }

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = "${msg.author}",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))

            Surface() {
                Text(
                    text = "${msg.body}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }


        }
    }


}

@Composable
fun Conversation(message: List<Message>){
    LazyColumn(){
        message.map { item { MessageCard(msg = it) } }
    }
}

@Preview
@Composable
fun PreviewConversation(){
    WanAndroidTheme() {
        Conversation(message = SampleData.conversationSample)
    }
}

@Composable
fun SpacerWithWidth(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}

@Preview
@Composable
fun MessageCardPreview() {
    WanAndroidTheme() {
        Surface() {
            MessageCard(
                msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
            )
        }
    }

}

data class Message(val author: String, val body: String)
