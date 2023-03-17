package com.example.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.core_ui.LocalSpacing
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_presentation.R
import com.example.tracker_presentation.components.NutrientInfo

@OptIn(ExperimentalCoilApi::class)
@Composable
fun TrackedFoodItem(
    trackedFood: TrackedFood,
    onDeleteClick: (TrackedFood) -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .padding(1.dp)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(5.dp),
            )
            .padding(end = spacing.small)
            .height(100.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(
                data = trackedFood.imageUrl,
                builder = {
                    crossfade(true)
                    error(R.drawable.ic_burger)
                    fallback(R.drawable.ic_burger)
                }
            ),
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(
                    RoundedCornerShape(
                        topStart = 5.dp,
                        bottomStart = 5.dp
                    )
                ),
            contentDescription = trackedFood.name
        )
        Spacer(modifier = Modifier.width(spacing.small))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = trackedFood.name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = MaterialTheme.typography.body1,
            )
            Spacer(Modifier.width(spacing.extraSmall))
            Text(
                text = stringResource(
                    R.string.nutrient_info,
                    trackedFood.amount,
                    trackedFood.calories
                ),
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(modifier = Modifier.width(spacing.small))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Default.Close,
                contentDescription = stringResource(R.string.delete),
                modifier = Modifier
                    .clickable {
                        onDeleteClick(trackedFood)
                    }
                    .align(Alignment.End),
            )
            Spacer(Modifier.height(spacing.extraSmall))
            Row {
                NutrientInfo(
                    name = stringResource(id = R.string.carbs),
                    unit = stringResource(R.string.grams),
                    amount = trackedFood.carbs,
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.body2
                )
                Spacer(Modifier.height(spacing.extraSmall))
                NutrientInfo(
                    name = stringResource(id = R.string.protein),
                    unit = stringResource(R.string.grams),
                    amount = trackedFood.protein,
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.body2
                )
                Spacer(Modifier.height(spacing.extraSmall))
                NutrientInfo(
                    name = stringResource(id = R.string.fat),
                    unit = stringResource(R.string.grams),
                    amount = trackedFood.fat,
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.body2
                )
            }
        }
    }
}
