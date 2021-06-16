package com.medipol.kotlinfinalprojesi.data.marka

import com.google.firebase.database.*
import com.medipol.kotlinfinalprojesi.data.MarkaItem
import com.medipol.kotlinfinalprojesi.utils.Resource

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class MarkaFirebaseDataSource: MarkaDataSource {


    override  fun markalariGetir(): Flow<Resource<List<MarkaItem>>> = callbackFlow {
        try {

            offer(Resource.Loading())


            val myRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Araclar")

            var markaListesi = arrayListOf<MarkaItem>()


            val subscription = myRef.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    for (postSnapshoot in dataSnapshot.getChildren()) {
                        var item = postSnapshoot.getValue(MarkaItem::class.java)!!
                        markaListesi.add(item)


                    }



                    offer(Resource.Success(markaListesi))
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

            awaitClose {  }

        } catch (e: Exception) {
            offer(Resource.Error(e))
            e.printStackTrace()
        }
    }


}
