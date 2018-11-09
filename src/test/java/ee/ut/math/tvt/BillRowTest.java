package ee.ut.math.tvt;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BillRowTest {

  private SaleItem item1;
  
  /**
   * Methods with @Before annotations will be invoked before each test is run.
   */
  @Before
  public void setUp() {
    item1 = new SaleItem("Lauaviin", 3.50); 
  }

  @Test
  public void testRowSumWithZeroQuantity() {
    BillRow r = new BillRow(item1, 0);
    
    assertEquals(r.getRowPrice(), 0.0, 0.0001);
  }
  
  @Test
  public void testRowSumWithoutDiscount() {
    BillRow r = new BillRow(item1, 1);
    assertEquals(r.getRowPrice(), 3.5, 0.001);
  }

  // TODO
  public void testRowSumWithDiscount() {}
  
  // TODO
  public void testRowSumWithInvalidDiscount() {}
  
  // TODO
  public void testRowSumWithNegativeQuantity() {}

  // TODO
  public void testRowSumWithNegativePrice() {}
  
}
