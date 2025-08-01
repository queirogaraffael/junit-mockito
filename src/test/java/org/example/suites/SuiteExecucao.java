package org.example.suites;

import org.example.services.LocacaoServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LocacaoServiceTest.class
}
)
public class SuiteExecucao {
}
