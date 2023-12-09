package com.vikash.poftdnasa

import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter


@Composable
fun screenView(modifier: Modifier= Modifier){
    val stateDescriptor: MainViewModel= viewModel()
    val finalDataHolder by stateDescriptor.stateHolderExposer
    Box(modifier = Modifier.fillMaxSize()){
        if (finalDataHolder.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }else if (finalDataHolder.error!=null){
            Text(text = "Error is ${finalDataHolder.error}")
        }else{
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = rememberAsyncImagePainter(model = finalDataHolder.localDataList.get(finalDataHolder.localDataList.size-1).url), contentDescription = null,
                    modifier= Modifier
                        .fillMaxSize()
                        .aspectRatio(1f))
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = finalDataHolder.localDataList.get(finalDataHolder.localDataList.size-1).title)
            }
        }

    }


}