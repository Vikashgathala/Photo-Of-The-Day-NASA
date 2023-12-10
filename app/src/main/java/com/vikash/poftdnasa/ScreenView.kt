package com.vikash.poftdnasa

import android.graphics.Paint
import android.graphics.Paint.Style
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import java.time.format.TextStyle


@Composable
fun screenView(modifier: Modifier= Modifier){
    val stateDescriptor: MainViewModel= viewModel()
    val finalDataHolder by stateDescriptor.stateHolderExposer
    Box(modifier = Modifier.fillMaxSize().background(Color.Black)){
        if (finalDataHolder.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }else if (finalDataHolder.error!=null){
            Text(text = "Error is ${finalDataHolder.error}")
        }else{
           LazyView(gridList = finalDataHolder.localDataList)
        }

    }


}


@Composable
fun LazyView(gridList: List<DataHandler>){
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize() ){
        items(gridList){
            item ->
            GridView(grid = item)
        }
    }
}


@Composable
fun GridView(grid: DataHandler){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = rememberAsyncImagePainter(model = grid.url), contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(10)))
        Text(text = grid.title, style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = grid.date, style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp))
    }

}