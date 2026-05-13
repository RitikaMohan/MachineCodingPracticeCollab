//Shopping Cart
//Each item has:
//quantity
//price

//Implement:
//fun increaseQuantity(itemId: String): Cart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.*
import androidx.compose.foundation.text.KeyboardOptions


@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App()
    }
}

@Composable
fun App() {
    MaterialTheme {
        var cart by remember{
            mutableStateOf(
                Cart(
                    listOf(
                        Item("Phone", 100),
                        Item("Laptop", 200)
                    )
                 )
            )
    	}
        
        ShoppingCart(
        	cart = cart,
        	onIncreaseClick = {id ->
            	cart = cart.increaseQuantity(id)
            }
        )
        
    }
}


data class Item(val id: String, 
                val price: Int,
               val quantity: Int = 1)

data class Cart(val items : List<Item> = emptyList()){
    
    fun increaseQuantity(id: String): Cart{
        var updatedItem = items.map{item ->
            if(item.id == id){
                item.copy(quantity = item.quantity + 1)
            }else{
                item
            }
        }
        return this.copy(items = updatedItem)
    }
}

@Composable
fun ShoppingCart(
	cart: Cart,
	onIncreaseClick: (String) -> Unit){
    
    Column{
        cart.items.forEach{item ->
            Column{
                Text("Item: ${item.id}")
                Text("Price: $${item.price}")
                Text("Quantity: ${item.quantity}")
                Button(onClick = { onIncreaseClick(item.id) }) {
                    Text("Increase")
                }
            }
            
        }
    }
}