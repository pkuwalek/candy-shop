package com.example.candyshop.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

enum class ItemState { Pressed, Idle }

fun Modifier.bounceClickWithColorRipple(color: Color, onClick: () -> Unit): Modifier = composed {
    // set default to inactive (idle)
    var itemState by remember { mutableStateOf(ItemState.Idle) }
    // animateFloatAsState requires end value and starts animation from current to specified
    val scale by animateFloatAsState(if (itemState == ItemState.Pressed) 0.95f else 1f)
        this
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = color),
                onClick = onClick
            )
            // handle pointer input
            .pointerInput(itemState) {
                awaitPointerEventScope {
                    itemState = if (itemState == ItemState.Pressed) {
                        waitForUpOrCancellation()
                        ItemState.Idle
                    } else {
                        awaitFirstDown(false)
                        ItemState.Pressed
                    }
                }
            }
}
