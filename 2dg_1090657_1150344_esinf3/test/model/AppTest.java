/*
 */
package model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import tree.AVL;
import tree.ArvorePoligonos;

/**
 *
 * @author pc asus
 */
public class AppTest {
    
    private static final String FX_NOME_LADOS = "teste_nome_lados.txt";
    private static final String FX_LADOS_NOMES = "teste_lados_nome.txt";

    /**
     * Test of lerDados method, of class App.
     */
    @Test
    public void testLerDados() {
        System.out.println("lerDados");
        App instance = new App();
        instance.lerDados();
        final int qtdUnidades = 9;
        final int qtdDezenas = 27;
        final int qtdCentenas = 9;
        final int qtdTotal = qtdUnidades + qtdDezenas + qtdCentenas;
        assertEquals("Numero de poligonos Unidades deve ser 9", instance.qtdPoligonosUnidades(), qtdUnidades);
        assertEquals("Numero de poligonos Dezenas deve ser 27", instance.qtdPoligonosDezenas(), qtdDezenas);
        assertEquals("Numero de poligonos Centenas deve ser 9", instance.qtdPoligonosCentenas(), qtdCentenas);
        
        assertEquals("O total de poligonos criados deve ser 45", instance.qtdPoligonosTotal(), qtdTotal);
    }

    /**
     * Test of construirPoligono method, of class App.
     */
    @Test
    public void testConstruirPoligono() {
        System.out.println("construirPoligono");
        int numlados = 119;
        App instance = new App();
        instance.lerDados();
        String expResult = "hectaenneakaidecagon";
        String result = instance.construirNomeDoPoligono(numlados);
        assertEquals(expResult, result);
    }

    /**
     * Test of numeroLados method, of class App.
     */
    @Test
    public void testNumeroLados() {
        System.out.println("numeroLados");
        App instance = new App();
        instance.lerDados();
        String nome = "dihectaheptacontahenagon";
        int numLados = 271;
        assertEquals("O poligono 'dihectaheptacontahenagon' tem 271 lados", instance.numeroLados(nome), numLados);
        nome = "heptahectaenneakaidecagon";
        numLados = 719;
        assertEquals("O poligono 'heptahectaenneakaidecagon' tem 719 lados", instance.numeroLados(nome), numLados);
    }

    /**
     * Test of construirArvoreTotal method, of class App.
     */
    @Test
    public void testConstruirArvoreTotal() {
        System.out.println("construirArvoreTotal");
        App instance = new App();
        instance.lerDados();
        ArvorePoligonos arvoreTotal = instance.construirArvorePoligonosTotal();
        assertEquals("O tamanho da arvore é 999", arvoreTotal.size(), 999);
        assertTrue("O menor elemento é o 'henagon' com 1 lado", arvoreTotal.smallestElement().equals(new Poligono(1, "henagon")));
    }

    /**
     * Testar o nome dos lados com o ficheiro de teste
     */
    @Test
    public void testNomeDadoOLado() {
        System.out.println("test NomeDadoOLado");
        App app = new App();
        app.lerDados();
        ArvorePoligonos arvore_pol_gerados = app.construirArvorePoligonosTotal();
        
        Ficheiro f = new Ficheiro();
        List<String> lista = f.lerFicheiro(AppTest.FX_LADOS_NOMES);
        
        List<String> listaNomePoligonos = new ArrayList<>();
        List<Integer> listaNumLados = new ArrayList<>();
        for (String s : lista) {
            String ls[] = s.split(";");
            listaNomePoligonos.add(ls[1]);
            listaNumLados.add(Integer.parseInt(ls[0]));
        }
        
        for (int i = 0; i < listaNumLados.size(); i++) {
            assertTrue("O nome do poligono corresponde ao lado", app.construirNomeDoPoligono(listaNumLados.get(i)).equals(listaNomePoligonos.get(i)));
        }
        
    }
    
    @Test
    public void testNumeroDadoONome() {
        System.out.println("test numeroDadoNome");
        App app = new App();
        app.lerDados();
        
        Ficheiro f = new Ficheiro();
        List<String> lista = f.lerFicheiro(AppTest.FX_NOME_LADOS);
        
        List<String> listaNomePoligonos = new ArrayList<>();
        List<Integer> listaNumLados = new ArrayList<>();
        for (String s : lista) {
            String ls[] = s.split(";");
            listaNomePoligonos.add(ls[0]);
            listaNumLados.add(Integer.parseInt(ls[1]));
        }
        for (int i = 0; i < listaNomePoligonos.size(); i++) {
            assertTrue("O nome do poligono corresponde ao lado", app.numeroLados(listaNomePoligonos.get(i)) == listaNumLados.get(i));
        }
        
    }

    /**
     * Test of poligonosIntervalo method, of class App.
     */
    @Test
    public void testPoligonosIntervalo() {
        System.out.println("poligonosIntervalo");
        App instance = new App();
        instance.lerDados();
        ArrayList<String> poligonos = (ArrayList<String>) instance.poligonosIntervalo(56, 827);
        int index = 0;
        assertEquals("Existem 771 poligonos de intervalo entre 56 e 827", poligonos.size(), 771);
        assertEquals("O index do octahectaicosihexagon é 0", index, poligonos.indexOf("octahectaicosihexagon"));
        index = 826 - 432;
        assertEquals("O index do tetrahectatriacontadigon é 394", index, poligonos.indexOf("tetrahectatriacontadigon"));
        index = 826 - 219;
        assertEquals("O index do dihectaenneakaidecagon é 0", index, poligonos.indexOf("dihectaenneakaidecagon"));
    }

    /**
     * Test of lowestCommonAncestor method, of class App.
     */
    @Test
    public void testLowestCommonAncestor() {
        System.out.println("lowestCommonAncestor");
        App instance = new App();
        instance.lerDados();
        String poligono1 = "triacontatrigon"; // 33
        String poligono2 = "pentacontaenneagon"; // 59
        Poligono p = new Poligono(48, "tetracontaoctagon");
        assertEquals("O poligono antessessor comum mais proximo é o 'tetracontaoctagon'", p, instance.lowestCommonAncestor(poligono1, poligono2));
    }
    
}
