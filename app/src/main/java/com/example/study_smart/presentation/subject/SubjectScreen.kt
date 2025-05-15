package com.example.study_smart.presentation.subject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.study_smart.presentation.components.CountCard
import com.example.study_smart.presentation.components.studySessionsList
import com.example.study_smart.presentation.components.taskList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val listState = rememberLazyListState()
    val isFABExtended by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
    Scaffold(
        topBar = {
            SubjectScreenTopBar(
                title = "English",
                onBackButtonClick = { /* TODO */ },
                onDeleteButtonClick = { /* TODO */ },
                onEditButtonClick = { /* TODO */ }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /* TODO */ },
                icon ={
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Task"
                    )
                      },
                text = {Text(text = "Add Task")},
                expanded = isFABExtended
            )
        }
    ) { paddingValue ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            item {
                SubjectOverViewSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    goalHours = "15",
                    studiedHours = "2",
                    progress = 0.75f
                )
            }
            taskList(
                sectionTitle = "UP COMING TASKS",
                tasks = emptyList(),
                emptyListText = "You don't have any tasks.\nClick the + button to add new task.",
                onCheckBoxClick = {},
                onTaskCardClick = {}
            )
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            taskList(
                sectionTitle = "COMPLETED TASKS",
                tasks = emptyList(),
                emptyListText = "You don't have any completed tasks.\nClick the check box on completion of tasks .",
                onCheckBoxClick = {},
                onTaskCardClick = {}
            )
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            studySessionsList(
                sectionTitle = "RECENT STUDY SESSIONS",
                emptyListText = "You don't have any recent study sessions.\n " +
                        "Start a study session to begin recording your progress.",
                sessions = emptyList(),
                onDeleteClickAction = { /* TODO */ }
            )

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SubjectScreenTopBar(
    title: String,
    onBackButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit
) {
    LargeTopAppBar(
        navigationIcon = {
            IconButton(
                onClick = { onBackButtonClick }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Navigation Back"
                )
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            IconButton(
                onClick = { onDeleteButtonClick }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Icon"
                )
            }
            IconButton(
                onClick = { onEditButtonClick }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Icon"
                )
            }
        }
    )
}

@Composable
private fun SubjectOverViewSection(
    modifier: Modifier,
    goalHours: String,
    studiedHours: String,
    progress: Float
) {
    var percentageProgress = remember(progress) {
        (progress * 100).toInt().coerceIn(0, 100)
    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Goal Study Hours",
            count = goalHours
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Study Hours",
            count = studiedHours
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier.size(75.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = 1f,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = progress,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
            )
            Text(text = "$percentageProgress")
        }
    }
}