package com.harshit.covidtracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.state.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val homeFragment: Fragment = HomeFragment()
        val stateFragment: Fragment = StateFragment()

        setCurrentFragment(homeFragment)
        fetchData(1)
        btm_nav.setOnItemSelectedListener { item->
//            fetchData()
            when(item.itemId){
                R.id.home->{
                    fetchData(1)
                    setCurrentFragment(homeFragment)
                }
                R.id.state-> {
                    fetchData(2)
                    setCurrentFragment(stateFragment)
                }
            }
            true
        }

    }

    private fun fetchData(id:Int) {
        GlobalScope.launch(Dispatchers.IO) {

            val response = withContext(Dispatchers.IO){ Client.callResponse.clone().execute()}

            if (response.isSuccessful){
                val data = Gson().fromJson(response.body?.string(), Response::class.java)
                //DT20218099220
                if(id ==1 ){
                    launch(Dispatchers.Main){
                        val summaryData = data.statewise[0]
                        tvActive.text  = summaryData.active
                        tvConfirm.text = summaryData.confirmed
                        tvRecovered.text = summaryData.recovered
                        tvDecreased.text = summaryData.deaths

                    }
                }
                if(id == 2){
                    launch (Dispatchers.Main){
                        bindStatewiseData(data.statewise.subList(1,data.statewise.size))
                    }
                }

            }


        }
    }

    private fun bindStatewiseData(subList: List<Statewise>) {
        val stateAdapter: StateAdapter = StateAdapter(subList)
        rvData.adapter = stateAdapter
    }


    private fun setCurrentFragment(fragment: Fragment)  =
        supportFragmentManager.beginTransaction().apply {

            replace(R.id.container,fragment)
            commit()
        }

}