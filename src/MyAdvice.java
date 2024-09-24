import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

public class MyAdvice {

    @Advice.OnMethodExit
    public static void intercept(
            @Advice.This Object x,
            @Advice.Argument(0) Object other,
            @Advice.Return(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object result /*此行非常关键，不可缺少*/) {

        System.out.println("Hello, world!");

        result = true;
    }
}
