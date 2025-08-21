package org.apache.pinot.core.query.aggregation.function;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.pinot.common.request.context.ExpressionContext;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DistinctCountAggregationFunctionTest {

    @Test
    public void testMergeIntSetWithLongSet() {
      DistinctCountAggregationFunction function = new DistinctCountAggregationFunction(
          List.of(ExpressionContext.forIdentifier("col")), false
      );
      Set<Integer> intermediateResult1 = new HashSet<>(Set.of(10));
      Set<Long> intermediateResult2 = new HashSet<>(Set.of(20L));
      intermediateResult1 = function.merge(intermediateResult1, intermediateResult2);

      Assert.assertEquals(intermediateResult1.size(), 2);
      Assert.assertTrue(intermediateResult1.contains(10L));
      Assert.assertTrue(intermediateResult1.contains(20L));;
    }

  @Test
  public void testMergeLongSetWithIntSet() {
    DistinctCountAggregationFunction function = new DistinctCountAggregationFunction(
        List.of(ExpressionContext.forIdentifier("col")), false
    );
    Set<Long> intermediateResult1 = new HashSet<>(Set.of(10L));
    Set<Integer> intermediateResult2 = new HashSet<>(Set.of(20));
    intermediateResult1 = function.merge(intermediateResult1, intermediateResult2);

    Assert.assertEquals(intermediateResult1.size(), 2);
    Assert.assertTrue(intermediateResult1.contains(10L));
    Assert.assertTrue(intermediateResult1.contains(20L));;
  }

  @Test
  public void testMergeSameClassType() {
    DistinctCountAggregationFunction function = new DistinctCountAggregationFunction(
        List.of(ExpressionContext.forIdentifier("col")), false
    );
    Set<Long> intermediateResult1 = new HashSet<>(Set.of(10L));
    Set<Long> intermediateResult2 = new HashSet<>(Set.of(20L));
    intermediateResult1 = function.merge(intermediateResult1, intermediateResult2);

    Assert.assertEquals(intermediateResult1.size(), 2);
    Assert.assertTrue(intermediateResult1.contains(10L));
    Assert.assertTrue(intermediateResult1.contains(20L));;

    Set<Integer> intermediateResult3 = new HashSet<>(Set.of(10));
    Set<Integer> intermediateResult4 = new HashSet<>(Set.of(20));
    intermediateResult3 = function.merge(intermediateResult3, intermediateResult4);

    Assert.assertEquals(intermediateResult3.size(), 2);
    Assert.assertTrue(intermediateResult3.contains(10));
    Assert.assertTrue(intermediateResult3.contains(20));;
  }
}
