import 'package:flutter/material.dart';
import 'package:khalti/khalti.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  _payViaKhalti() {
    Khalti(
      urlSchemeIOS: "KhaltiPayFlutterExampleScheme",
      publicKey: "test_public_key_15903a44695e4e409a98aa3fcb281c4c",
      productId: "sku1212",
      productName: "TRaj Test Laptop",
      amount: 1000,
      customData: {
        "expample": "khalti payment",
      },
    ).initPayment(
      onSuccess: (data) {
        print("success");
        print(data);
      },
      onError: (error) {
        print("error");
        print(error);
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Flutter Khalti Payment Example'),
        ),
        body: Center(
          child: FlatButton.icon(
            icon: Icon(Icons.payment),
            label: Text("Pay Using Khalti"),
            onPressed: () {
              _payViaKhalti();
            },
          ),
        ),
      ),
    );
  }
}
