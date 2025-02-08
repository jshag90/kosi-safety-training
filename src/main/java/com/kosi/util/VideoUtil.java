package com.kosi.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoUtil {
    // OS에 따라 FFmpeg 경로 설정
    private static final String FFMPEG_PATH = getFfmpegPath();
    private static String getFfmpegPath() {
        return FilesUtil.isWindows() ? "C:\\ffmpeg\\bin\\ffmpeg.exe" : "/usr/bin/ffmpeg";
    }

    public static String getVideoDuration(String filePath) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    FFMPEG_PATH, "-i", filePath
            );
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            Pattern pattern = Pattern.compile("Duration: (\\d+):(\\d+):(\\d+)\\.(\\d+)");

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    int hours = Integer.parseInt(matcher.group(1));
                    int minutes = Integer.parseInt(matcher.group(2));
                    int seconds = Integer.parseInt(matcher.group(3));
                    int milliseconds = Integer.parseInt(matcher.group(4));

                    // 밀리초 반올림하여 초 증가 처리
                    if (milliseconds > 0) {
                        seconds++;
                    }

                    // 초가 60이 넘으면 분 증가
                    if (seconds >= 60) {
                        seconds = 0;
                        minutes++;
                    }

                    // 분이 60이 넘으면 시간 증가
                    if (minutes >= 60) {
                        minutes = 0;
                        hours++;
                    }

                    // 결과 문자열 포맷팅
                    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
                }
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "재생 시간 확인 실패";
    }
}
