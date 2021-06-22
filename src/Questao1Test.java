import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Questao1Test {

    @Test
    public void test() throws IOException {
        Questao1 questao1 = new Questao1();
        assertEquals(questao1.pesquisa("2q95XoeFGixx8b5LNF6Ey1"), "2q95XoeFGixx8b5LNF6Ey1 ## [Pitbull, Flo Rida, LunchMoney Lewis] ## Greenlight (feat. Flo Rida & LunchMoney Lewis) ## 22/07/2016 ## 0.245 ## 0.695 ## 0.0 ## 0.3670000000000001 ## -5.617000000000001 ## 0.281 ## 0.8370000000000001 ## 244174");
        assertEquals(questao1.pesquisa("5sKb4ShexCtnNcZlWHGDrE"), "5sKb4ShexCtnNcZlWHGDrE ## [Beach House] ## On the Sea ## 15/05/2012 ## 0.725 ## 0.247 ## 0.0494 ## 0.0938 ## -7.664 ## 0.0334 ## 0.521 ## 332467");
        assertEquals(questao1.pesquisa("2DcmVSLAg0ZzfOQxDshZHY"), "2DcmVSLAg0ZzfOQxDshZHY ## [A-Lin] ## 給我一個理由忘記 ## 08/07/2011 ## 0.583 ## 0.399 ## 0.0 ## 0.104 ## -5.801 ## 0.0357 ## 0.512 ## 285547");

        assertEquals(questao1.pesquisa(""), "Música não encontrada");
        assertEquals(questao1.pesquisa("1234"), "Música não encontrada");
        assertEquals(questao1.pesquisa("----"), "Música não encontrada");
        assertEquals(questao1.pesquisa("DcmVSLAg0ZzfOQxDshZHYY"), "Música não encontrada");
        assertEquals(questao1.pesquisa("2DcmVSLAg0ZzfOQxDshZH"), "Música não encontrada");
    }

}