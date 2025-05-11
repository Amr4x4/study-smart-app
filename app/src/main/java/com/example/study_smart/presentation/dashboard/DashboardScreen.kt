package com.example.study_smart.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.study_smart.R
import com.example.study_smart.domine.model.Subject
import com.example.study_smart.presentation.components.CountCard
import com.example.study_smart.presentation.components.SubjectCard
import com.example.study_smart.presentation.components.studySessionsList
import com.example.study_smart.presentation.components.taskList

@Composable
fun DashboardScreen() {
    Scaffold(
        topBar = { DashboardScreenTopBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                CountCardsSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    subjectCount = 15,
                    studiedHours = "10",
                    goalStudy = "15"
                )
            }
            item {
                SubjectCardsSection(
                    modifier = Modifier.fillMaxWidth(),
                    subjectList = emptyList(),
                    onAddIconClicked = {},
                    onSubjectCardClick = {}
                )
            }
            item{
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp, vertical = 20.dp)
                ) {
                    Text(
                        text = "Start Study Session"
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            taskList(
                sectionTitle = "UP COMING TASKS",
                tasks = emptyList(),
                emptyListText = "You don't have any tasks.\nClick the + button to add new task.",
                onCheckBoxClick = {},
                onTaskCardClick = {}
            )
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
private fun DashboardScreenTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.study_smart),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )
}

@Composable
private fun CountCardsSection(
    modifier: Modifier = Modifier,
    subjectCount: Int,
    studiedHours: String,
    goalStudy: String
) {
    Row(modifier = modifier) {
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = stringResource(R.string.subject_count),
            count = "$subjectCount"
        )
        Spacer(Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = stringResource(R.string.studied_hours),
            count = studiedHours
        )
        Spacer(Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = stringResource(R.string.goal_study_hours),
            count = goalStudy
        )
    }
}

@Composable
private fun SubjectCardsSection(
    modifier: Modifier,
    subjectList: List<Subject>,
    emptyListText: String = "You don't have any subjects.\nClick the + button to add new subject.",
    onAddIconClicked: () -> Unit,
    onSubjectCardClick: (Int?) -> Unit
) {
    Column(modifier = modifier.padding(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.subjects),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
            IconButton(onClick = onAddIconClicked) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_subject) // Add to strings.xml
                )
            }
        }
        if (subjectList.isEmpty()) {
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.img_books),
                contentDescription = emptyListText
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = emptyListText,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        } else {
            LazyRow (
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
            ){
                items(subjectList) { subject ->
                    SubjectCard(
                        subjectName = subject.name,
                        gradientColor = subject.colors,
                        onClick = { }
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewDashboardScreen() {
    DashboardScreen()
}
