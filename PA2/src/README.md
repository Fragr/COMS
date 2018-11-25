# COMS 311 PA2 WGraph & ImageProcessor
For my Computer Science 311 Programming Assignment 2 I was tasked with completing two classes: WGraph and ImageProcessor. The main goal of this assignment was to implement different variations of the shortest path problem for graphs. I chose to use my own implementation of Dijkstra's in order to satisfy that requirement.

## WGraph
![wgraph](https://user-images.githubusercontent.com/40704571/48816945-ca572f00-ed09-11e8-87f8-dfa43b191fda.PNG)

The goal of this class is to take as input a string, containing the filename from which to pull information from. This information is what will be used to build a directed graph. After building the graph the goal is to implement 3 variations of the shortest path problem: V2V, V2S, & S2S.
- V2V:

![wgraphv2v](https://user-images.githubusercontent.com/40704571/48817349-ad236000-ed0b-11e8-94a2-d21a9e430bce.PNG)

- V2S:

![wgraphv2s](https://user-images.githubusercontent.com/40704571/48817476-45214980-ed0c-11e8-8073-368373534dee.PNG)

- S2S:

![wgraphs2s](https://user-images.githubusercontent.com/40704571/48817531-7d288c80-ed0c-11e8-92f9-39789fe2a53d.PNG)

### Output of WGraph
Here is the input I will use in order to calculate the shortest path.

![wgraphv2v 0 0-3 0](https://user-images.githubusercontent.com/40704571/48961213-77af8a00-ef38-11e8-957e-75c589093e2d.PNG)

I calculate the shortest path from (0,0) to (3,0) which gives me: [0, 0, 1, 3, 3, 1, 3, 0]

Next I calculate the shortest path from (0,0) to (),(),(), etc which gives me:

Lastly I calculate the shortest path from (),(),(), etc to (),(),(), etc which gives me:

## ImageProcessor
![imageprocessor](https://user-images.githubusercontent.com/40704571/48817009-14d8ab80-ed0a-11e8-975d-7a1e361406b0.PNG)

The goal of this class is to take as input a string, containing the filename from which to pull information from. This information is what will be used to build an image of pixels, containing the RGB values. After building the image the goal is to be able to reduce the image width by a value of k (such that W-k > 1). When reducing the image width by 1 just removing a vertical line of pixels won't do. So in order to know which pixels to remove we must calculate the importance for every given pixel in the image. From this point starting at the top row (0th row) we will use the importance values to calcuate the shortest path from the top row to the last row. Once we have calculated the shortest path from top to bottom we must remove these pixels, reorder the pixels and repeat. We do this k times. After the last interation we will write the end pixel values to a file. To do this we had to implement two methods: getImportance & writeReduced.
- getImportance: Calculates the importance based on these requirements for every pixel.

![imageprocessorimportance](https://user-images.githubusercontent.com/40704571/48817616-ed371280-ed0c-11e8-8291-2f5b0ececbd6.PNG)

- writeReduced: Takes as input k (number of times to reduce the width) & FName (name of file to write to). Computes the new image matrix after reducing the width by k. And writes the image matrix to the file following the same format as the input.

![imageprocesswritereduced](https://user-images.githubusercontent.com/40704571/48817804-d2b16900-ed0d-11e8-9229-55cf4bac4ff0.PNG)

### Output of ImageProcessor