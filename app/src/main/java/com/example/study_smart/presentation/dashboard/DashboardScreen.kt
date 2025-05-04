package com.example.study_smart.presentation.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.study_smart.R
import com.example.study_smart.presentation.components.CountCard
// start -> 17:21
@Composable
fun DashboardScreen() {
    Scaffold (
        topBar = { DashboardScreenTopBar() }
    ){ paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
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
    subjectCount:Int,
    studiedHours:String,
    goalStudy:String
) {
    Row(
        modifier = modifier
    ) {
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = stringResource(R.string.subject_count),
            count = "$subjectCount"
        )
        Spacer(Modifier.padding(horizontal = 10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = stringResource(R.string.studied_hours),
            count = studiedHours
        )
        Spacer(Modifier.padding(horizontal = 10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = stringResource(R.string.goal_study_hours),
            count = goalStudy
        )
    }
}

@Composable
private fun SubjectCardsSection(modifier: Modifier = Modifier) {
    Column {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "SUBJECTS",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 12.dp)
            )
            IconButton(
                onClick = {/* TODO */}
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_subject)
                )
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewDashboardScreen() {
    DashboardScreen()
}