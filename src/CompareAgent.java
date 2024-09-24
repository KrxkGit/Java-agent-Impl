import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import java.lang.instrument.Instrumentation;


public class CompareAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("CompareAgent.premain()");

        AgentBuilder agentBuilder = new AgentBuilder.Default()
                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                .with(AgentBuilder.InitializationStrategy.NoOp.INSTANCE)
                .with(AgentBuilder.TypeStrategy.Default.REDEFINE)
                .ignore(ElementMatchers.nameStartsWith("net.bytebuddy."));

        agentBuilder.type(ElementMatchers.named("java.math.BigInteger"))
                .transform((builder, typeDescription, classLoader, module, protectionDomain) -> builder
                        .visit(
                                Advice.to(MyAdvice.class)
                                .on(ElementMatchers.named("equals"))
                        ))
                .installOn(inst);
    }

}
