# AvCalc
*A Simple Honkai Star Rail **a**ction **v**alue **calc**ulator.*

<img width="800" alt="image" src="https://github.com/user-attachments/assets/cfd494c4-fdcf-4fef-a6eb-7c6633e36a70" />


> !!! This project has served its purpose as a prototype of a full stack web app, and development is paused for now !!!

## Project Structure:
- Spring-boot backend
- Vite + React /w TypeScript

### Features:
- Generate timeline with character actions, as well as setting the total action value for the simulator to visualize
- Drag action advance tokens to the timeline at the desired point to visualize changes in actions/timing

### Missing features:
- Custom total action value selection
- Custom action advance amount for each token
- Clear tokens from the timeline
- Breakpoint table should be up-to-date with action advance tokens/have a togglebale option/indicate required action value to next action

### How to run:
- Navigate to `/avcalc` and run `./mvnw spring-boot:run`
- In another terminal, navigate to `/react/app` and run `npm install` followed by `npm run dev`

### Credits:
- [Wallpaper](https://www.goodfon.com/games/wallpaper-honkai-series-firefly-trailblazer-stelle-honkai-star-rail-de.html) by NyankoSensei
- [nvim-java](https://github.com/nvim-java/nvim-java) is an amazing Java plugin for Neovim!
