package com.going.jetpackstudy

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import org.junit.Test
import java.util.concurrent.DelayQueue
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

class KotlinCoroutinesTest {


    @Test
    fun KtCoroutinesTest() {
//        lunchTest()
//        runblockingTest()
//        cancleJoinTest()
        aWaitTest()
    }

    //region  协程 lunch函数
    // 异步, 不阻塞线程
    private fun lunchTest() {
        val time = measureTimeMillis {
            GlobalScope.launch {
                Thread.sleep(1000)
                print("第一个${Thread.currentThread()}\n")
            }
            GlobalScope.launch {
                Thread.sleep(1000)
                print("第二个${Thread.currentThread()}\n")
            }
            print("第三个${Thread.currentThread()}\n")
            Thread.sleep(2000)
        }
        print("一共耗时 $time\n")


    }
//endregion

    //runblocking
    //该函数会阻塞线程
    private fun runblockingTest() {
        val time = measureTimeMillis {
            runBlocking {
                print("这是delay前\n")
                delay(1000)
                print("这是delay后\n")
            }
            print("( ⊙ o ⊙ )啊！\n")

        }
        print("一共耗时 $time\n")
    }

    // cancle join test
    //等待join的协程对象执行完毕 才会往下进行
    private fun cancleJoinTest() {
        val time = measureTimeMillis {
            runBlocking {
                val time = measureTimeMillis {

                    val L1: Job = GlobalScope.launch {
                        print("这个是L1    ${Thread.currentThread()}\n")
                    }
                    val L2: Job = GlobalScope.launch {
                        print("这个是L2    ${Thread.currentThread()}\n")
                    }
                    val A2: Deferred<Unit> = async {
                        repeat(3) {
                            print("这个是A2    ${Thread.currentThread()}\n")
                            delay(200)
                        }
                    }
//                    A2.cancelAndJoin()
                    A2.join()//join() 会等待当前协程执行完毕才会往下执行
                    val A1: Deferred<Unit> = async {
                        print("这个是A1    ${Thread.currentThread()}\n")
//                        A2.cancel()
                        delay(1000)
                    }
                    A1.join()
                    print("小外部 小外部 ${Thread.currentThread()}\n")

                }
                print("大外部 大外部 $time\n")
            }
        }
        print("最外部最外部 $time ${Thread.currentThread()}\n")
    }


    /**
     * await  相当于挂起函数 会将当前函数挂起 等待执行结果
     */
    private fun aWaitTest() {
        val time = measureTimeMillis {
            runBlocking {
                val A1: Deferred<String> = async {
                    delay(3000)
                    println("111")
                    "执行完毕耗时操作的A111"
                }
//                A1.join()
                val A2: Deferred<String> = async {
                    delay(1000)
                    println("222")
                    "执行完毕耗时操作的A222"
                }
//                A2.join()
                println("${A1.await()}${A2.await()}")
//                println("${A1.getCompleted()}${A2.getCompleted()}")
            }

        }

        println("一共耗时$time")

    }


}


