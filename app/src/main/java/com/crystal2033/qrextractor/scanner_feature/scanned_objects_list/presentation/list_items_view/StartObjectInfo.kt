package com.crystal2033.qrextractor.scanner_feature.scanned_objects_list.presentation.list_items_view

import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crystal2033.qrextractor.core.LOG_TAG_NAMES
import com.crystal2033.qrextractor.core.model.Keyboard
import com.crystal2033.qrextractor.core.model.WorkSpace
import java.time.LocalDate

@Composable
fun StartObjectInfo(
    image: ImageBitmap,
    text: String,
    modifier: Modifier = Modifier,
    onObjectClicked: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable{
                onObjectClicked()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            bitmap = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(RectangleShape)
                .align(Alignment.CenterVertically),

            )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = text,
            color = Color.LightGray,
            modifier = Modifier.align(Alignment.CenterVertically),
            fontSize = 15.sp
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun StartObjectInfoPreview() {
    val bitmap = BitmapFactory.decodeFile("D:\\Картинки\\blue-space.jpg")
    val keyboard = Keyboard(
        id = 1,
        image = bitmap.asImageBitmap(),
        model = "Corsair M80-411B-20",
        startUseDate = LocalDate.now(),
        workSpace = WorkSpace(5)
    )
    StartObjectInfo(
        image = keyboard.image,
        text = keyboard.model,
        onObjectClicked = {
            Log.i(LOG_TAG_NAMES.INFO_TAG, "Clicked on scanned object ${keyboard.javaClass.simpleName} ${keyboard.id}")
        }
    )
}