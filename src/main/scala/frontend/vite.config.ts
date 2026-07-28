import { defineConfig, ViteDevServer } from "vite";
import tailwindcss from "@tailwindcss/vite";

const http4sBackendWatchPlugin = () => {
    let backendWasDown = false;
    const BACKEND_URL = 'http://localhost:8080/health';

    return {
        name: 'http4s-backend-watch',
        configureServer(server: ViteDevServer) {
            setInterval(async () => {
                try {
                    await fetch(BACKEND_URL);
                    if (backendWasDown) {
                        console.log('➡️ http4s server came back online! Triggering browser reload...');
                        backendWasDown = false;
                        server.ws.send({ type: 'full-reload' });
                    }
                } catch (error) {
                    if (!backendWasDown) {
                        backendWasDown = true;
                        console.log("Backend is down!");
                    }
                }
            }, 1000)
        }
    }
}

export default defineConfig({
    plugins: [
        http4sBackendWatchPlugin(),
        tailwindcss(),
    ],
    server: {
      port: 5173,
      strictPort: true,
      cors: true
    },
    build: {
        outDir: "../../resources/public/assets",
        emptyOutDir: true,
        rollupOptions: {
            input: "main.css",
            output: {
                assetFileNames: "app.css"
            }
        }
    }
});