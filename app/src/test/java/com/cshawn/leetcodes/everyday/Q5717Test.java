package com.cshawn.leetcodes.everyday;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.util.stream.Stream;

/**
 * @author C.Shawn
 * @date 2021/4/17 11:27 下午
 */
class Q5717Test {
    public static class DataArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(new int[]{1,1,1}, 3),
                    Arguments.of(new int[]{1,5,2,4,1}, 14),
                    Arguments.of(new int[]{3}, 0),
                    Arguments.of(new int[]{3,5}, 0),
                    Arguments.of(new int[]{3,0}, 4)
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(DataArgumentsProvider.class)
    void minOperations(int[] nums, int result) {
        Assertions.assertEquals(result, new Q5717().minOperations(nums));
    }
}