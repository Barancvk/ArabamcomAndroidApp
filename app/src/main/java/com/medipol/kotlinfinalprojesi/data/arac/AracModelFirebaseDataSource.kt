package com.medipol.kotlinfinalprojesi.data.arac

import com.google.firebase.database.*
import com.medipol.kotlinfinalprojesi.data.AracModelItem
import com.medipol.kotlinfinalprojesi.utils.Constants
import com.medipol.kotlinfinalprojesi.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AracModelFirebaseDataSource: AracModelDataSource {
    override fun modelleriGetir(): Flow<Resource<List<AracModelItem>>> = callbackFlow {
        try {

            offer(Resource.Loading())


            val myRef: Query = FirebaseDatabase.getInstance().getReference("AracModel").orderByChild("AracKategoriBilgisi").equalTo(Constants.marka)

            var modelListesi = arrayListOf<AracModelItem>()


            val subscription = myRef.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    for (postSnapshoot in dataSnapshot.getChildren()) {
                        var item = postSnapshoot.getValue(AracModelItem::class.java)!!
                        modelListesi.add(item)


                    }



                    offer(Resource.Success(modelListesi))
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

            awaitClose {

            }

        } catch (e: Exception) {
            offer(Resource.Error(e))
            e.printStackTrace()
        }
    }
}