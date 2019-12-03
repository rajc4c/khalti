package com.codeforcore.khalti

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import android.content.Context
import khalti.checkOut.api.Config
import khalti.checkOut.api.OnCheckOutListener
import khalti.checkOut.helper.KhaltiCheckOut

class KhaltiPlugin: MethodCallHandler {

  companion object {
    lateinit var context: Context
    lateinit var channel: MethodChannel
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      context = registrar.activeContext()
      channel = MethodChannel(registrar.messenger(), "khalti")
      channel.setMethodCallHandler(KhaltiPlugin())
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "initPayment") {
      initKhaltiPayment(call.arguments as HashMap<*, *>)
    } else {
      result.notImplemented()
    }
  }


  fun initKhaltiPayment(params: HashMap<*, *>) {
    val config = Config(params["publicKey"] as String, params["productId"] as String, params["productName"] as String, params["productUrl"] as String, (params["amount"] as Double).toLong(), params["customData"] as HashMap<String, String>, object : OnCheckOutListener {
      override fun onSuccess(data: HashMap<String, Any>) {
        channel.invokeMethod("paymentSucess", data)
      }

      override fun onError(action: String, message: String) {
        var errorMessage: HashMap<String, String> = HashMap()
        errorMessage["action"] = action
        errorMessage["message"] = message
        channel.invokeMethod("paymentFailed", errorMessage)

      }
    })
    val khaltiCheckOut = KhaltiCheckOut(context, config)
    khaltiCheckOut.show()
  }
}
