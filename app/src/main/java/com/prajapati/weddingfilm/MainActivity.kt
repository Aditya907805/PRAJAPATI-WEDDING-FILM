package com.prajapati.weddingfilm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Black = Color(0xFF0B0B0B)
private val Panel = Color(0xFF171717)
private val Gold = Color(0xFFD4AF57)
private val Cream = Color(0xFFF4E7C2)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { WeddingFilmApp() }
    }
}

@Composable
fun WeddingFilmApp() {
    var selected by remember { mutableStateOf("Home") }
    MaterialTheme(colorScheme = darkColorScheme(
        background = Black, surface = Panel, primary = Gold,
        onPrimary = Black, onBackground = Cream, onSurface = Cream
    )) {
        Scaffold(containerColor = Black, bottomBar = {
            NavigationBar(containerColor = Panel, contentColor = Gold) {
                listOf("Home", "Gallery", "Services", "Contact").forEach { item ->
                    NavigationBarItem(
                        selected = selected == item, onClick = { selected = item },
                        icon = { Icon(when(item) {
                            "Gallery" -> Icons.Default.PhotoLibrary
                            "Services" -> Icons.Default.CameraAlt
                            "Contact" -> Icons.Default.Chat
                            else -> Icons.Default.Favorite
                        }, contentDescription = item) },
                        label = { Text(item) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Gold, selectedTextColor = Gold,
                            indicatorColor = Color(0xFF3A2E17),
                            unselectedIconColor = Cream, unselectedTextColor = Cream
                        )
                    )
                }
            }
        }) { padding ->
            Column(Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState())
                .background(Black).padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                when(selected) {
                    "Home" -> HomeScreen({selected="Gallery"}, {selected="Contact"})
                    "Gallery" -> GalleryScreen()
                    "Services" -> ServicesScreen()
                    else -> ContactScreen()
                }
                Spacer(Modifier.height(18.dp))
                Text("CAPTURING YOUR FOREVER MOMENTS", color=Gold.copy(alpha=.8f),
                    fontSize=10.sp, letterSpacing=2.sp, textAlign=TextAlign.Center)
            }
        }
    }
}

@Composable
fun BrandHeader() {
    Text("✦", color=Gold, fontSize=26.sp)
    Text("PRAJAPATI", color=Gold, fontSize=32.sp, fontWeight=FontWeight.Bold, letterSpacing=4.sp)
    Text("W E D D I N G   F I L M", color=Cream, fontSize=12.sp, letterSpacing=2.sp)
    Spacer(Modifier.height(14.dp))
    HorizontalDivider(color=Gold.copy(alpha=.65f))
}

@Composable
fun GoldButton(label:String, onClick:()->Unit) {
    Button(onClick=onClick, modifier=Modifier.fillMaxWidth(),
        colors=ButtonDefaults.buttonColors(containerColor=Gold, contentColor=Black),
        shape=RoundedCornerShape(12.dp)) {
        Text(label, fontWeight=FontWeight.Bold, letterSpacing=1.sp)
    }
}

@Composable
fun FeatureRow(icon:ImageVector, title:String) {
    Row(Modifier.fillMaxWidth().padding(vertical=7.dp)
        .background(Panel,RoundedCornerShape(12.dp)).padding(14.dp),
        verticalAlignment=Alignment.CenterVertically) {
        Icon(icon,null,tint=Gold); Spacer(Modifier.width(12.dp))
        Text(title,color=Cream,fontWeight=FontWeight.Medium)
    }
}

@Composable
fun HomeScreen(onGallery:()->Unit,onContact:()->Unit) {
    BrandHeader(); Spacer(Modifier.height(28.dp))
    Box(Modifier.fillMaxWidth().height(210.dp).background(Panel,RoundedCornerShape(18.dp)),
        contentAlignment=Alignment.Center) {
        Column(horizontalAlignment=Alignment.CenterHorizontally) {
            Icon(Icons.Default.Favorite,null,tint=Gold,modifier=Modifier.size(46.dp))
            Spacer(Modifier.height(12.dp))
            Text("YOUR STORY, BEAUTIFULLY TOLD",color=Cream,fontWeight=FontWeight.SemiBold)
            Text("Wedding • Pre-Wedding • Cinematic Films",color=Gold,fontSize=12.sp)
            Text("Add your wedding photographs in the Gallery section",color=Cream.copy(alpha=.65f),
                fontSize=10.sp,textAlign=TextAlign.Center,modifier=Modifier.padding(12.dp))
        }
    }
    Spacer(Modifier.height(24.dp))
    Text("Every moment deserves to be remembered.",color=Cream,fontSize=19.sp,
        fontWeight=FontWeight.Medium,textAlign=TextAlign.Center)
    Spacer(Modifier.height(8.dp))
    Text("Premium wedding photography and cinematic films for your special day.",
        color=Cream.copy(alpha=.72f),textAlign=TextAlign.Center)
    Spacer(Modifier.height(22.dp))
    GoldButton("EXPLORE OUR WORK",onGallery)
    Spacer(Modifier.height(10.dp))
    OutlinedButton(onClick=onContact,border=ButtonDefaults.outlinedButtonBorder,
        colors=ButtonDefaults.outlinedButtonColors(contentColor=Gold),modifier=Modifier.fillMaxWidth()) {
        Text("BOOK YOUR DATE")
    }
    Spacer(Modifier.height(24.dp))
    FeatureRow(Icons.Default.Movie,"Cinematic Wedding Films")
    FeatureRow(Icons.Default.CameraAlt,"Wedding Photography")
    FeatureRow(Icons.Default.Star,"Pre-Wedding Shoots")
}

@Composable
fun GalleryScreen() {
    BrandHeader(); Spacer(Modifier.height(20.dp))
    Text("OUR PORTFOLIO",color=Gold,fontSize=22.sp,fontWeight=FontWeight.Bold)
    Text("Replace sample tiles with your own approved wedding photos and film links.",
        color=Cream.copy(alpha=.7f),textAlign=TextAlign.Center,fontSize=12.sp)
    Spacer(Modifier.height(18.dp))
    listOf("Wedding Stories","Pre-Wedding","Cinematic Highlights","Traditional Moments").forEach {
        Box(Modifier.fillMaxWidth().padding(vertical=6.dp).height(105.dp)
            .background(Panel,RoundedCornerShape(14.dp)),contentAlignment=Alignment.Center) {
            Column(horizontalAlignment=Alignment.CenterHorizontally) {
                Icon(Icons.Default.PhotoLibrary,null,tint=Gold,modifier=Modifier.size(28.dp))
                Text(it,color=Cream,fontWeight=FontWeight.SemiBold)
                Text("Photo / video showcase placeholder",color=Gold.copy(alpha=.7f),fontSize=10.sp)
            }
        }
    }
}

@Composable
fun ServicesScreen() {
    BrandHeader(); Spacer(Modifier.height(20.dp))
    Text("OUR SERVICES",color=Gold,fontSize=22.sp,fontWeight=FontWeight.Bold)
    val items = listOf(
        "Wedding Photography" to "Beautifully composed moments from your wedding day.",
        "Cinematic Wedding Films" to "A story-led film capturing the emotion of your celebration.",
        "Pre-Wedding Shoot" to "Creative portraits and films before your big day.",
        "Engagement & Events" to "Photography and coverage for your special occasions.",
        "Albums & Highlights" to "Keepsake albums and short highlight films."
    )
    items.forEach { (title,desc) ->
        Card(colors=CardDefaults.cardColors(containerColor=Panel),
            modifier=Modifier.fillMaxWidth().padding(vertical=6.dp),shape=RoundedCornerShape(14.dp)) {
            Column(Modifier.padding(16.dp)) {
                Text(title,color=Gold,fontWeight=FontWeight.Bold,fontSize=16.sp)
                Spacer(Modifier.height(5.dp)); Text(desc,color=Cream.copy(alpha=.8f),fontSize=13.sp)
            }
        }
    }
}

@Composable
fun ContactNumber(number:String,onClick:()->Unit) {
    Card(colors=CardDefaults.cardColors(containerColor=Panel),
        modifier=Modifier.fillMaxWidth().padding(vertical=5.dp).clickable { onClick() }) {
        Row(Modifier.padding(16.dp),verticalAlignment=Alignment.CenterVertically) {
            Icon(Icons.Default.Call,null,tint=Gold); Spacer(Modifier.width(12.dp))
            Text(number,color=Cream,fontSize=18.sp,fontWeight=FontWeight.SemiBold)
            Spacer(Modifier.weight(1f)); Text("CALL",color=Gold,fontSize=12.sp)
        }
    }
}

@Composable
fun ContactScreen() {
    val context=LocalContext.current
    BrandHeader(); Spacer(Modifier.height(22.dp))
    Text("LET'S MAKE IT MEMORABLE",color=Gold,fontSize=20.sp,
        fontWeight=FontWeight.Bold,textAlign=TextAlign.Center)
    Text("Call us to discuss your date, event and photography requirements.",
        color=Cream.copy(alpha=.75f),textAlign=TextAlign.Center)
    Spacer(Modifier.height(20.dp))
    ContactNumber("8235927133") {
        context.startActivity(Intent(Intent.ACTION_DIAL,Uri.parse("tel:8235927133")))
    }
    ContactNumber("7257988360") {
        context.startActivity(Intent(Intent.ACTION_DIAL,Uri.parse("tel:7257988360")))
    }
    Spacer(Modifier.height(16.dp))
    GoldButton("WHATSAPP: 8235927133") {
        val url="https://wa.me/918235927133?text="+Uri.encode("Hello PRAJAPATI WEDDING FILM, I would like to enquire about wedding photography.")
        context.startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(url)))
    }
    Spacer(Modifier.height(10.dp))
    OutlinedButton(onClick={
        val url="https://wa.me/917257988360?text="+Uri.encode("Hello PRAJAPATI WEDDING FILM, I would like to enquire about wedding photography.")
        context.startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(url)))
    },modifier=Modifier.fillMaxWidth(),colors=ButtonDefaults.outlinedButtonColors(contentColor=Gold)) {
        Text("WHATSAPP: 7257988360")
    }
    Spacer(Modifier.height(18.dp))
    Text("Business name: PRAJAPATI WEDDING FILM",color=Cream,fontSize=12.sp)
}
