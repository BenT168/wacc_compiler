package backend.generators;

import antlr.WACCParser;
import org.antlr.v4.runtime.misc.NotNull;

public interface CodeGeneratorInterface {
    void process(@NotNull WACCParser.ProgramContext ctx);
}
