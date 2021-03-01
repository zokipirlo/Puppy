package com.example.androiddevchallenge.ui.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.PetData
import com.example.androiddevchallenge.data.PetType


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FilterComponent(
    selectedType: String?,
    selectedBreed: String?,
    items: PetType,
    onTypeSelected: (String?) -> Unit,
    onBreedSelected: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    val typeItems = when (selectedType == null) {
        true -> items.map { it.first }
        false -> listOf(selectedType)
    }
    val breedItems = when (selectedType == null) {
        true -> null
        false -> when (selectedBreed == null) {
            true -> items.find { it.first == selectedType }?.second
            false -> listOf(selectedBreed)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        SubFilter(
            items = typeItems,
            itemSelected = selectedType != null,
            onItemSelected = onTypeSelected
        )
        Spacer(modifier = Modifier.width(8.dp))
        SubFilter(
            items = breedItems ?: emptyList(),
            itemSelected = selectedBreed != null,
            onItemSelected = onBreedSelected
        )
        Spacer(modifier = Modifier.width(if (breedItems != null) 16.dp else 8.dp))
    }
}

@Composable
fun SubFilter(
    items: List<String>,
    itemSelected: Boolean,
    onItemSelected: (String?) -> Unit
) {
    FilterItemsWrapper(itemSelected) { backgroundColor, contentColor ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.animateContentSize()
        ) {
            items.forEachIndexed { index, item ->
                if (index != 0) {
                    HorizontalDivider(
                        color = contentColor,
                        thickness = 1.dp
                    )
                }
                FilterItem(
                    item = item,
                    backgroundColor = backgroundColor,
                    contentColor = contentColor,
                    itemSelected = itemSelected,
                    onItemSelected = onItemSelected
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun FilterItem(
    item: String,
    backgroundColor: Color,
    contentColor: Color,
    itemSelected: Boolean,
    onItemSelected: (String?) -> Unit
) {
    Button(
        colors = when (itemSelected) {
            true -> ButtonDefaults.textButtonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor
            )
            false -> ButtonDefaults.textButtonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor
            )
        },
        onClick = {
            when (itemSelected) {
                true -> onItemSelected(null)
                false -> onItemSelected(item)
            }
        }
    ) {
        val icon = PetData.getIconForType(item)
        if (icon != null) {
            Icon(
                painter = painterResource(icon),
                contentDescription = item,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        Text(
            text = item
        )
        AnimatedVisibility(visible = itemSelected) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Clear filter",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun FilterItemsWrapper(isSelected: Boolean, content: @Composable (backgroundColor: Color, contentColor: Color) -> Unit) {
    val color = when (isSelected) {
        true -> MaterialTheme.colors.secondary
        false -> MaterialTheme.colors.primary
    }
    val contentColor = contentColorFor(color)
    Surface(
        color = color,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = contentColor
        ),
        content = {
            content(color, contentColor)
        }
    )
}

@Preview("None selected")
@Composable
fun FilterPreview() {
    FilterComponent(
        selectedType = null,
        selectedBreed = null,
        items = PetData.petTypes,
        onTypeSelected = {},
        onBreedSelected = {})
}

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface.copy(alpha = DividerAlpha),
    thickness: Dp = 1.dp,
    startIndent: Dp = 0.dp
) {
    val indentMod = if (startIndent.value != 0f) {
        Modifier.padding(start = startIndent)
    } else {
        Modifier
    }
    Box(
        modifier
            .then(indentMod)
            .fillMaxHeight()
            .width(thickness)
            .background(color = color)
    )
}

private const val DividerAlpha = 0.12f