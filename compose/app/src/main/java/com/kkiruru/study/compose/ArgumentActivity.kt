package com.kkiruru.study.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

class ArgumentActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xFFFAFAFA))
                        .statusBarsPadding()
                        .padding(horizontal = 20.dp)
                ) {
                    AddressItem(Modifier)

                    Spacer(modifier = Modifier.height(10.dp))

                    SubContent(
                        modifier = Modifier
                    ){
                        Text(
                            text = "배송지",
                            color = Color(0xFF212121),
                            fontWeight = FontWeight.W600
                        )
                        Text(
                            modifier = Modifier.padding(top = 2.dp),
                            text = "용산구 세탁로 1701, 런드리고 10층 1005호",
                            color = Color(0xFF212121),
                            fontWeight = FontWeight.W600
                        )
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DefaultPreview(
    modifier: Modifier = Modifier
) {
    Column {
        AddressItem(Modifier)
        Spacer(modifier = Modifier.height(10.dp))
        SubContent(
            modifier = Modifier
        ){
            Text(
                text = "배송지",
                style = MaterialTheme.typography.titleLarge ,
                color = Color(0xFF212121),
                fontWeight = FontWeight.W600
            )
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = "용산구 세탁로 1701, 런드리고 10층 1005호",
                color = Color(0xFF212121),
                fontWeight = FontWeight.W600
            )
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = "배송기사님께 전달할 내용을 적어주세요.",
                color = Color(0xFF212121),
                fontWeight = FontWeight.W600
            )
        }
    }
}



@Composable
private fun SubContent(
    modifier: Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                shape = RoundedCornerShape(10.dp),
                elevation = 7.dp,
                ambientColor = Color(0x40939393),
                spotColor = Color(0x40939393),
            )
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column (
                modifier = Modifier.weight(1.0f),
            ) {
                content()
            }

            Image(
                modifier = Modifier.wrapContentSize(),
                painter = painterResource(id = R.drawable.ic_system_chevron),
                contentDescription = null,
            )
        }
    }
}


@Composable
fun AddressItem(
    modifier: Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                shape = RoundedCornerShape(10.dp),
                elevation = 7.dp,
                ambientColor = Color(0x40939393),
                spotColor = Color(0x40939393),
            )
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column (
                modifier = Modifier.weight(1.0f),
            ) {
                Text(
                    text = "배송지",
                    color = Color(0xFF212121),
                    fontWeight = FontWeight.W600
                )
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = "용산구 세탁로 1701, 런드리고 10층 1005호",
                    color = Color(0xFF212121),
                    fontWeight = FontWeight.W600
                )
            }

            Image(
                modifier = Modifier.wrapContentSize(),
                painter = painterResource(id = R.drawable.ic_system_chevron),
                contentDescription = null,
            )
        }
    }
}
