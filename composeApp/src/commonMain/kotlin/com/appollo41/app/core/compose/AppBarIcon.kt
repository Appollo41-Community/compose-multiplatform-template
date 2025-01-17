package com.appollo41.app.core.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.appollo41.app.theme.AppTheme

@Composable
fun AppBarIcon(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 24.dp,
    enabled: Boolean = true,
    tint: Color = LocalContentColor.current,
    enabledBackgroundColor: Color = Color.Unspecified,
    disabledBackgroundColor: Color = AppTheme.colorScheme.outline,
    appBarIconContentDescription: String? = null,
) {
    IconButton(
        modifier = modifier
            .clip(CircleShape)
            .background(
                color = if (enabled) enabledBackgroundColor else disabledBackgroundColor,
            ),
        enabled = enabled,
        onClick = onClick,
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            imageVector = icon,
            contentDescription = appBarIconContentDescription,
            tint = tint,
        )
    }
}
