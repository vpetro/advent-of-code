module Main (main) where

testData :: [Int]
testData = [199, 200, 208, 210, 200, 207, 240, 269, 260, 263]


solve :: [Int] -> Int
solve d = sum $ zipWith converter d $ drop 1 d 
  where
    converter :: Int -> Int -> Int
    converter i j = if i < j then 1 else 0


main :: IO ()
main = print result
  where
    result = solve testData
