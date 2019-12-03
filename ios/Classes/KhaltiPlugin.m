#import "KhaltiPlugin.h"
#import <khalti/khalti-Swift.h>

@implementation KhaltiPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftKhaltiPlugin registerWithRegistrar:registrar];
}
@end
